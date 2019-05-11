package com.example.fms_app.Service

import android.os.Build
import android.support.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TransformDate(date: String){
    private val date = date
    init {

    }
    @RequiresApi(Build.VERSION_CODES.O)

    //format dd-MM-yyyy ex.02-05-2019
    fun getThaiDate_1():String{
        var dd = date.substring(0, 2)
        var mm = date.substring(3, 5)
        var yy = date.substring(6, 10).toInt()
        if(dd == "01") { dd =="1"; }
        else if(dd=="02") { dd="2"; }
        else if(dd=="03") { dd="3"; }
        else if(dd=="04") { dd="4"; }
        else if(dd=="05") { dd="5"; }
        else if(dd=="06") { dd="6"; }
        else if(dd=="07") { dd="7"; }
        else if(dd=="08") { dd="8"; }
        else if(dd=="09") { dd="9"; }

        if(mm=="01") { mm="ม.ค."; }
        else if(mm=="02") { mm="ก.พ."; }
        else if(mm=="03") { mm="มี.ค."; }
        else if(mm=="04") { mm="เม.ย."; }
        else if(mm=="05") { mm="พ.ค."; }
        else if(mm=="06") { mm="มิ.ย."; }
        else if(mm=="07") { mm="ก.ค."; }
        else if(mm=="08") { mm="ส.ค."; }
        else if(mm=="09") { mm="ก.ย."; }
        else if(mm=="10") { mm="ต.ค."; }
        else if(mm=="11") { mm="พ.ย."; }
        else if(mm=="12") { mm="ธ.ค."; }
        yy += 543

        return "${dd} ${mm} ${yy}"
    }
}