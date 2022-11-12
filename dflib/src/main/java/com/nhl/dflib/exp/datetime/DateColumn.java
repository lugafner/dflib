package com.nhl.dflib.exp.datetime;

import com.nhl.dflib.DateExp;
import com.nhl.dflib.exp.Column;

import java.time.LocalDate;

/**
 * @since 0.11
 */
public class DateColumn extends Column<LocalDate> implements DateExp {

    public DateColumn(String name) {
        super(name, LocalDate.class);
    }

    public DateColumn(int position) {
        super(position, LocalDate.class);
    }

    @Override
    public String toQL() {
        return position >= 0 ? "$date(" + position + ")" : name;
    }
}
