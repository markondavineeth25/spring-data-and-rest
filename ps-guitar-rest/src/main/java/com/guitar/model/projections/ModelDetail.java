package com.guitar.model.projections;

import java.math.BigDecimal;

import org.springframework.data.rest.core.config.Projection;

import com.guitar.model.Manufacturer;
import com.guitar.model.Model;
import com.guitar.model.ModelType;

@Projection(name="modelDetail", types= {Model.class})
public interface ModelDetail {

	public String getName();
	public BigDecimal getPrice();
	public Manufacturer getManufacturer();
	public ModelType getModelType();
	public int getFrets();
}
