package com.ltg.framework.util.date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * <p> ClassName: MonthLocalDate </p>
 * <p> Package: com.ltg.framework.util.date </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/7 - 22:36
 * @Version: v1.0
 */

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MonthLocalDate {

    private LocalDate firstDay;
    private LocalDate lastDay;
}
