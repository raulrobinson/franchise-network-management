package com.network.franchise.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request DTO for creating a franchise")
public class AddBranchToFranchiseExistsRequestDto {
    @Schema(description = "Name of the branch", example = "Branch 1")
    private String name;
}
