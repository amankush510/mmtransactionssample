package com.example.sample.utils;

import com.example.sample.exception.errors.ErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/***
 * This class is used to get the error messages for error codes in ErrorCodeEnum from error.properties file
 */
public enum ErrorCodeUtil {
    //Single instance for this class/enum
    INSTANCE;

    private final Logger LOG = LoggerFactory.getLogger(ErrorCodeUtil.class);
    private Properties props;

    /***
     * This constructor loads the error.properties file in props
     */
    ErrorCodeUtil() {
        InputStream in = null;
        try {
            //Reading the error.properties file
            in = getClass().getClassLoader().getResourceAsStream("error.properties");
            props = new Properties();

            //Loading the content of error.properties file
            props.load(in);
        } catch (Exception ex) {
            // Ignore error
            LOG.error("Error occurred while loading error properties.");
        } finally {
            //Closing the stream if it is not null
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ioe) {
                    // Ignore error;
                    LOG.error("Error occurred while closing InputStream in ErrorUtil.");
                }
            }
        }
    }

    /***
     * This method is used to get the error code for the given instance of ErrorCodeEnum
     *
     * @param errorCode ErrorCodeEnum instance
     *
     * @return returns the error code for the provided ErrorCodeEnum
     */
    public String getErrorDescription(ErrorCodeEnum errorCode) {
        return errorCode != null && props != null ? props.getProperty(errorCode.getErrorCode()) : null;
    }

    /***
     * This method is used to get the error message for the given instance of ErrorCodeEnum
     *
     * @param errorCode ErrorCodeEnum instance
     * @param args List of strings for dynamic values in error message for e.g ''{0}'' requires one string
     *
     * @return returns the error code for the provided ErrorCodeEnum
     */
    public String getErrorDescription(ErrorCodeEnum errorCode, Object ...args) {
        //Get error code for the given ErrorCodeEnum instance
        String errorDescription = getErrorDescription(errorCode);
        if (errorDescription == null) {
            return null;
        }
        if (args == null) {
            return errorDescription;
        }
        try {
            //Replace placeholders for dynamic values
            return MessageFormat.format(errorDescription, args);
        } catch (Exception ex) {
            // Ignore error and return errorDescription as is.
            LOG.warn("Review errorCode usage '{}'", errorCode);
            return errorDescription;
        }
    }
}

