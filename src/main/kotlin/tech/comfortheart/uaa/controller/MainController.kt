package tech.comfortheart.uaa.controller

import com.fasterxml.jackson.annotation.JsonFormat
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.apache.commons.beanutils.BeanUtils
import java.util.*

@Controller
class MainController {
    @Post("/note")
    fun newNote(@Body note: Note) :String {
        val myNote = MyNote()
        BeanUtils.copyProperties(myNote, note)
        return "${myNote.name}, is created at ${myNote.date}"
    }

    data class Note(val name: String,
                    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS") val date: Date)

    data class MyNote(var name: String?=null, var date: Date?=null)


}