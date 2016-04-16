package com.kohler.entity.extend;

import com.kohler.entity.SysLogEntity;
import com.kohler.entity.SysUserEntity;
/**
 * Class Function Description
 *
 * @author fujiajun
 * @Date 2014年10月21日
 */
public class SyslogEntityPojo extends SysLogEntity {
        /**
     * 
     */
    private static final long serialVersionUID = -947058663451571569L;
        private SysUserEntity sysuserentity;

        public SysUserEntity getSysuserentity() {
            return sysuserentity;
        }

        public void setSysuserentity(SysUserEntity sysuserentity) {
            this.sysuserentity = sysuserentity;
        }
}
