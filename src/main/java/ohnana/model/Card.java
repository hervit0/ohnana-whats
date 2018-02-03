package ohnana.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ohnana.configuration.SwaggerStaticContent;
import ohnana.model.generic.AttributeInterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card")
public class Card implements AttributeInterface<Card> {
    @Id
    @Column(name = "card_id")
    @ApiModelProperty(notes = SwaggerStaticContent.CARD_ID)
    private UUID id;

    @Column(name = "name")
    @ApiModelProperty(notes = SwaggerStaticContent.CARD_NAME)
    private String name;


    @Override
    public String toString() {
        return "Card";
    }
}
