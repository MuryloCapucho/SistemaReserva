package br.com.systemmcr.sistemareserva.abstracts.po;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractPO < T > implements Comparable< T >{
	
	public abstract int hashCode();
	
	public abstract boolean equals (Object obj);
	
	public abstract String toString();
	
}
