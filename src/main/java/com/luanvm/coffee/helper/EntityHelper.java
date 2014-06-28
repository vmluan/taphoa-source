/**
 * Copyright (c) 2013 THKAssociates,Inc. All rights reserved.
 * Filename   : EntityHelper.java
 * Description: 
 * @author    : Khanh.Pham
 * Created    : Sep 3, 2013
 */


package com.luanvm.coffee.helper;

public interface EntityHelper<T> {

    T copyFrom(final T entity);

    T copyWithoutPkFrom(final T entity);

    T updateFrom(final T fromentity, T toEntity);

    T createEntityInstance();

    T createRandomEntity();
}
