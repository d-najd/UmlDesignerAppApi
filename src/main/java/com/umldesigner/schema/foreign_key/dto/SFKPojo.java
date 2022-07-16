package com.umldesigner.schema.foreign_key.dto;

import com.umldesigner.infrastructure.pojo.pojos.BaseMPojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
/**
 * @apiNote value1 and value2 in the identity refer to id's of SItems
 */
public class SFKPojo extends BaseMPojo {

    private String onDelete = "ca";

    private String onUpdate = "ca";

}