package com.squad5.fifo.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity @Data
@Builder
public class Metodo {

	@Id
	@GeneratedValue
	private Long id;

	private String estado;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoMetodo tipoMetodo;

}