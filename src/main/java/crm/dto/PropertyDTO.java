package crm.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDTO {
    private Long id;
    private String title;
    private Double price;
    private String location;
    private String type;
    private String status;
    private String createdAt;
}