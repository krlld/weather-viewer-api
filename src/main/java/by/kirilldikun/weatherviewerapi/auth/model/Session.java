package by.kirilldikun.weatherviewerapi.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    private UUID id;

    private Long userId;

    private LocalDateTime expiresAt;
}
