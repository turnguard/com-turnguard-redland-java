package com.turnguard.redland.exception;

import com.turnguard.redland.utils.GError;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public class RedlandException extends Exception {
        private int errorCode = -1;
        
        public RedlandException(GError error) {
            super(error.getMessage());
            this.errorCode = error.getCode();
        }

        public int getErrorCode() {
            return errorCode;
        }
        
    
}
