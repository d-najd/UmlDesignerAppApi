package com.umldesigner.schema.table_item.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.umldesigner.infrastructure.domain.BaseEntity;
import com.umldesigner.infrastructure.domain.BaseMTMEntity;
import com.umldesigner.infrastructure.domain.BaseMTMIdentity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "schema_reference") 
//note this does not work properly, need to implement it in simular way to below
public class SchemaTableItem extends BaseMTMEntity {
    private static final long serialVersionUID = 3L;
}

/*

@Table(name="boards_tasks_join")
@Entity
public class BoardTaskJoin {
	
    @EmbeddedId
    private BtjIdentity btjIdentity;
    
	public BoardTaskJoin() {
		super();
	}

	public BtjIdentity getBtjIdentity() {
		return btjIdentity;
	}
}
*/