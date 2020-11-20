package com.squad5.fifo.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity @Data
@Builder
public class HisPartida {

	@Id
	private long id;

	private String resultado;

	@ManyToOne(optional = false)
	private HisNode hisNode;

	@ManyToOne(optional = false)
	private Jogo jogo;

}
