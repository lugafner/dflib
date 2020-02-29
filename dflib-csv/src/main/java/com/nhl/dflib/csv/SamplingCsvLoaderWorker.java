package com.nhl.dflib.csv;

import com.nhl.dflib.DataFrame;
import com.nhl.dflib.Index;
import com.nhl.dflib.IntSeries;
import com.nhl.dflib.Series;
import com.nhl.dflib.csv.loader.AccumulatorColumn;
import com.nhl.dflib.series.builder.IntAccumulator;
import org.apache.commons.csv.CSVRecord;

import java.util.Iterator;
import java.util.Random;

/**
 * Loads a row sample from a potentially large CSV row iterator with the specified sample size.
 */
class SamplingCsvLoaderWorker implements CsvLoaderWorker {

    protected AccumulatorColumn<?>[] columns;
    protected Index columnIndex;

    private int rowSampleSize;
    private Random rowsSampleRandom;
    private IntAccumulator sampledRows;

    SamplingCsvLoaderWorker(
            Index columnIndex,
            AccumulatorColumn<?>[] columns,
            int rowSampleSize,
            Random rowsSampleRandom) {

        this.columnIndex = columnIndex;
        this.columns = columns;

        this.rowSampleSize = rowSampleSize;
        this.rowsSampleRandom = rowsSampleRandom;
        this.sampledRows = new IntAccumulator();
    }

    @Override
    public DataFrame load(Iterator<CSVRecord> it) {
        consumeCSV(it);
        return toDataFrame();
    }

    protected DataFrame toDataFrame() {
        return sortSampled(toUnsortedDataFrame());
    }

    protected DataFrame toUnsortedDataFrame() {
        int width = columnIndex.size();
        Series<?>[] series = new Series[width];
        for (int i = 0; i < width; i++) {
            series[i] = columns[i].toSeries();
        }

        return DataFrame.newFrame(columnIndex).columns(series);
    }

    protected DataFrame sortSampled(DataFrame sampledUnsorted) {
        IntSeries sortIndex = sampledRows.toSeries().sortIndexInt();
        return sampledUnsorted.selectRows(sortIndex);
    }

    protected void consumeCSV(Iterator<CSVRecord> it) {
        int width = columnIndex.size();
        int i = 0;
        while (it.hasNext()) {
            CSVRecord row = it.next();
            sampleRow(i++, width, row);
        }
    }

    protected void sampleRow(int rowNumber, int width, CSVRecord row) {

        // Reservoir sampling algorithm per https://en.wikipedia.org/wiki/Reservoir_sampling

        // fill "reservoir" first
        if (rowNumber < rowSampleSize) {
            addRow(width, row);
            sampledRows.addInt(rowNumber);
        }
        // replace previously filled values based on random sampling with decaying probability
        else {
            int pos = rowsSampleRandom.nextInt(rowNumber + 1);
            if (pos < rowSampleSize) {
                replaceRow(pos, width, row);
                sampledRows.setInt(pos, rowNumber);
            }
        }
    }

    protected void addRow(int width, CSVRecord record) {
        for (int i = 0; i < width; i++) {
            columns[i].add(record);
        }
    }

    protected void replaceRow(int pos, int width, CSVRecord record) {
        for (int i = 0; i < width; i++) {
            columns[i].set(pos, record);
        }
    }
}
