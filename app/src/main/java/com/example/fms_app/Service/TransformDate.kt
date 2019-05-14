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

    fun getDateforDb_1():String{
        var dd = String()
        var mm = String()
        var yy: Int? =  null
        if(date.length == 12){
            dd = date.substring(0, 2)
            mm = date.substring(3, 7)
            yy = date.substring(8, 12).toInt()
        }else{
            dd = date.substring(0, 1)
            mm = date.substring(2, 6)
            yy = date.substring(7, 11).toInt()
        }

        if(dd == "1") { dd =="01"; }
        else if(dd=="2") { dd="02"; }
        else if(dd=="3") { dd="03"; }
        else if(dd=="4") { dd="04"; }
        else if(dd=="5") { dd="05"; }
        else if(dd=="6") { dd="06"; }
        else if(dd=="7") { dd="07"; }
        else if(dd=="8") { dd="08"; }
        else if(dd=="9") { dd="09"; }

        if(mm=="ม.ค.") { mm="01"; }
        else if(mm=="ก.พ.") { mm="02"; }
        else if(mm=="ก.พ.") { mm="03"; }
        else if(mm=="ม.ย.") { mm="04"; }
        else if(mm=="พ.ค.") { mm="05"; }
        else if(mm=="มิ.ย.") { mm="06"; }
        else if(mm=="ก.ค.") { mm="07"; }
        else if(mm=="ส.ค.") { mm="08"; }
        else if(mm=="ก.ย.") { mm="09"; }
        else if(mm=="ต.ค.") { mm="10"; }
        else if(mm=="พ.ย.") { mm="11"; }
        else if(mm=="ธ.ค.") { mm="12"; }
        yy -= 543

        return "${yy}-${mm}-${dd}"
    }
}