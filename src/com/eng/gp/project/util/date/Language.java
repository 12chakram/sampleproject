package com.eng.gp.project.util.date;

import java.util.Locale;

public enum Language{
    en_GB,
    en_US,
    es_PY,
    pl_TRANSLATE,
    pl_IDENT,
    pl_REPLACE,
    pl_PREPOST
    ;

    public Locale getLocale(){
        String [] localeBits = this.toString().split("_");
        return new Locale(localeBits[0], localeBits[1]);
    }
}