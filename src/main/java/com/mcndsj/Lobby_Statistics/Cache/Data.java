package com.mcndsj.Lobby_Statistics.Cache;

import com.mcndsj.Lobby_Statistics.Utils.SQLUtils;

import java.util.HashMap;

/**
 * Created by Matthew on 2016/4/14.
 */
public class Data {
    private HashMap<String,Object> theMap;
    private DataType type = null;
    private boolean isInvalid = false;


    public Data(DataType type){
        this.theMap = new HashMap<String,Object>();
        this.type = type;
    }

    public void setInvalid(){
        this.isInvalid = true;
    }

    public DataType getType(){
        return this.type;
    }

    public boolean isInvalid(){
        return this.isInvalid;
    }

    public void put(String key, Object value){
        this.theMap.put(key,value);
    }

    public Object get(String key) {
        if(theMap.containsKey(key)){
            Object str = theMap.get(key);
            if(str instanceof Double){
                str = String.valueOf(str);
                char[] set = ((String)str).toCharArray();
                int position = 0;
                for(int i = 0 ; i < set.length ; i ++){
                    if(set[i] == '.'){
                        position = i;
                        break;
                    }
                }
                if(position + 3  >= set.length) {
                    position = set.length;
                }else{
                    position += 3;
                }
                str = ((String)str).substring(0, position);
            }
            return str;
        }else{
           return null;
        }
    }

    public void update() {
        SQLUtils.getFullRow((String)(theMap.get(type.getSQLKeyName())),type,this);
        this.isInvalid = false;
    }
}
