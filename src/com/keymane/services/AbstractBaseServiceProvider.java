package com.keymane.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractBaseServiceProvider implements BaseService{

    protected final Log logger = LogFactory.getLog(getClass().getName());
    
}
