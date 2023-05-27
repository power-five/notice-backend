package powerfive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import powerfive.domain.Role

@SpringBootApplication
class NoticeApplication

fun main(args: Array<String>) {
    runApplication<NoticeApplication>(*args)
    val valueOf = Role.valueOf("USER");
    print(valueOf);
}
