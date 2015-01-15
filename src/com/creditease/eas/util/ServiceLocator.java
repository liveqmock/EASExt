package com.creditease.eas.util;

/**
 * Locates and provides all available application services.
 */
public class ServiceLocator
{

    private ServiceLocator()
    {
        // shouldn't be instantiated
    }

    /**
     * The shared instance of this ServiceLocator.
     */
    private final static ServiceLocator instance = new ServiceLocator();

    /**
     * Gets the shared instance of this Class
     *
     * @return the shared service locator instance.
     */
    public static final ServiceLocator instance()
    {
        return instance;
    }

    /**
     * The bean factory reference instance.
     */
    private org.springframework.beans.factory.access.BeanFactoryReference beanFactoryReference;
    
    /**
     * The bean factory reference location.
     */
    private String beanFactoryReferenceLocation;
    
    /**
     * The bean factory reference id.
     */
    private String beanRefFactoryReferenceId;

    /**
     * Initializes the Spring application context from
     * the given <code>beanFactoryReferenceLocation</code>.  If <code>null</code>
     * is specified for the <code>beanFactoryReferenceLocation</code>
     * then the default application context will be used.
     *
     * @param beanFactoryReferenceLocation the location of the beanRefFactory reference.
     */
    public synchronized void init(final String beanFactoryReferenceLocation, final String beanRefFactoryReferenceId)
    {
        this.beanFactoryReferenceLocation = beanFactoryReferenceLocation;
        this.beanRefFactoryReferenceId = beanRefFactoryReferenceId;
        this.beanFactoryReference = null;
    }

    /**
     * Initializes the Spring application context from
     * the given <code>beanFactoryReferenceLocation</code>.  If <code>null</code>
     * is specified for the <code>beanFactoryReferenceLocation</code>
     * then the default application context will be used.
     *
     * @param beanFactoryReferenceLocation the location of the beanRefFactory reference.
     */
    public synchronized void init(final String beanFactoryReferenceLocation)
    {
        this.beanFactoryReferenceLocation = beanFactoryReferenceLocation;
        this.beanFactoryReference = null;
    }
    
    /**
     * The default bean reference factory location.
     */
    private final String DEFAULT_BEAN_REFERENCE_LOCATION = "beanRefFactory.xml";
    
    /**
     * The default bean reference factory ID.
     */
    private final String DEFAULT_BEAN_REFERENCE_ID = "beanRefFactory";

    /**
     * Gets the Spring ApplicationContext.
     */
    protected synchronized org.springframework.context.ApplicationContext getContext()
    {
        if (this.beanFactoryReference == null)
        {
            if (this.beanFactoryReferenceLocation == null)
            {
                this.beanFactoryReferenceLocation = DEFAULT_BEAN_REFERENCE_LOCATION;
            }
            if (this.beanRefFactoryReferenceId == null)
            {
                this.beanRefFactoryReferenceId = DEFAULT_BEAN_REFERENCE_ID;
            }
            org.springframework.beans.factory.access.BeanFactoryLocator beanFactoryLocator =
                org.springframework.context.access.ContextSingletonBeanFactoryLocator.getInstance(
                    this.beanFactoryReferenceLocation);
            this.beanFactoryReference = beanFactoryLocator.useBeanFactory(this.beanRefFactoryReferenceId);
        }
        return (org.springframework.context.ApplicationContext)this.beanFactoryReference.getFactory();
    }

    /**
     * Shuts down the ServiceLocator and releases any used resources.
     */
    public synchronized void shutdown()
    {
        if (this.beanFactoryReference != null)
        {
            this.beanFactoryReference.release();
            this.beanFactoryReference = null;
        }
    }



    /**
     * Gets an instance of the given service.
     */
    public final Object getService(String serviceName)
    {
        return getContext().getBean(serviceName);
    }
    /**
     * 
     * 描述：日志记录的服务
     * 2013-1-24 下午10:08:47 by ygq
     * @version
     * @return
     */
    public final com.creditease.eas.hr.service.BaseLogRecordService getBaseLogRecordService()
    {
        return (com.creditease.eas.hr.service.BaseLogRecordService)
            getContext().getBean("baseLogRecordService");
    }
}