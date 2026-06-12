package crm.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String status;
    private String createdAt;
}