package org.dflib.echarts.render;

import org.dflib.echarts.render.option.axis.AxisModel;
import org.dflib.echarts.render.option.DataSetModel;
import org.dflib.echarts.render.option.SeriesModel;
import org.dflib.echarts.render.option.toolbox.ToolboxModel;
import org.dflib.echarts.render.option.tooltip.TooltipModel;

import java.util.List;

/**
 * @since 1.0.0-M21
 */
public class OptionModel {

    private final DataSetModel dataset;
    private final boolean legend;
    private final List<SeriesModel> series;
    private final String title;
    private final ToolboxModel toolbox;
    private final TooltipModel tooltip;
    private final AxisModel xAxis;
    private final List<AxisModel> yAxes;

    public OptionModel(
            DataSetModel dataset,
            boolean legend,
            List<SeriesModel> series,
            String title,
            ToolboxModel toolbox,
            TooltipModel tooltip,
            AxisModel xAxis,
            List<AxisModel> yAxes) {

        this.title = title;
        this.toolbox = toolbox;
        this.tooltip = tooltip;
        this.dataset = dataset;
        this.xAxis = xAxis;
        this.yAxes = yAxes;
        this.series = series;
        this.legend = legend;
    }

    public String getTitle() {
        return title;
    }

    public TooltipModel getTooltip() {
        return tooltip;
    }

    public ToolboxModel getToolbox() {
        return toolbox;
    }

    public DataSetModel getDataset() {
        return dataset;
    }

    public AxisModel getXAxis() {
        return xAxis;
    }

    public boolean isYAxesPresent() {
        return yAxes != null;
    }

    public List<AxisModel> getYAxes() {
        return yAxes;
    }

    public List<SeriesModel> getSeries() {
        return series;
    }

    public boolean isLegend() {
        return legend;
    }
}
