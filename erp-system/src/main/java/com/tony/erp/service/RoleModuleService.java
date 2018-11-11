package com.tony.erp.service;

import com.tony.erp.dao.RoleModuleMapper;
import com.tony.erp.domain.Module;
import com.tony.erp.domain.RoleModule;
import com.tony.erp.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleModuleService {

    @Autowired
    private RoleModuleMapper roleModuleMapper;

    @Autowired
    private ModuleService moduleService;

    /**
     * 更新角色及其权限信息
     * @param mids
     * @param rid
     * @return
     */
    public int insert(List<String> mids,String rid){
        List<String> exists=this.selectMidByRid(rid);
        int result=0;
        if(!CollectionUtils.isEmpty(mids)&&!StringUtils.isEmpty(rid)){
            if(CollectionUtils.isEmpty(exists)){
                List<RoleModule> inserRM=new ArrayList<>();
                mids.forEach(mid->{
                    RoleModule rm=new RoleModule(rid,mid);
                    inserRM.add(rm);
                });
                result= roleModuleMapper.insert(inserRM);
            }else {
                List<String> insert=ListUtils.difference(mids,exists);
                List<String> delete=ListUtils.difference(exists,mids);
                List<RoleModule> inserRM=new ArrayList<>();
                if(!CollectionUtils.isEmpty(insert)){
                    insert.forEach(mid->{
                        RoleModule rm=new RoleModule(rid,mid);
                        inserRM.add(rm);

                    });
                    result=roleModuleMapper.insert(inserRM);
                }
               if(!CollectionUtils.isEmpty(delete)){
                   delete.forEach(mid->{
                       RoleModule roleModule=new RoleModule(rid,mid);
                       roleModuleMapper.delete(roleModule);
                   });
               }
            }
            return result;
        }
        return -1;
    }


    /**
     * 获取所有的mids
     * @return
     */
    public List<String> getAllMids(){
        List<Module> modules=moduleService.getAllModules();
        List<String> mids=new ArrayList<>();
        modules.forEach(module -> {
            mids.add(module.getMid());
        });

        return mids;
    }

    /**
     * 根据rid查找所有的mids
     * @param rid
     * @return
     */
    public List<String> selectMidByRid(String rid){
        return roleModuleMapper.selectMidByRid(rid);
    }

    /**
     * 根据rid删除所有权限信息
     * @param rid
     * @return
     */
    public int deleteByRid(String rid){
        return roleModuleMapper.deleteByRid(rid);
    }
}
