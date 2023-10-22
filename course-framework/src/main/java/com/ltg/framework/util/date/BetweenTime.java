package com.ltg.framework.util.date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p> ClassName: BetweenTime </p>
 * <p> Package: com.ltg.framework.util.date </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/7 - 22:40
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BetweenTime {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
