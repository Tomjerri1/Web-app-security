package edu.maksimchuk.lab1;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class DemoController {

    // Публічний доступ — без логіну
    @GetMapping("/public/hello")
    public String publicHello() {
        return "Public hello — доступ без логіну";
    }

    // USER-доступ
    @GetMapping("/user/profile")
    public String userProfile(Authentication auth) {
        return "Доступ дозволено користувачу: " + auth.getName() + " (роль: USER)";
    }

    // MANAGER доступ до статистики
    @GetMapping("/manage/stats")
    public String managerStats(Authentication auth) {
        return "Доступ дозволено: " + auth.getName() + " (роль: MANAGER)";
    }

    // ADMIN доступ
    @GetMapping("/admin/panel")
    public String adminPanel(Authentication auth) {
        return "Панель адміністратора — доступ дозволено: " + auth.getName() + " (роль: ADMIN)";
    }

    // USER+, MANAGER, ADMIN можуть читати
    @GetMapping("/data/read")
    public String readData(Authentication auth) {
        return "Read data — доступ дозволено: " + auth.getName();
    }

    // USER не може писати
    @PostMapping("/data/write")
    public String writeData(Authentication auth) {
        return "Доступ дозволено: " + auth.getName() + " (ROLE_ADMIN або ROLE_MANAGER)";
    }

    // ADMIN-only create
    @PostMapping("/admin/create")
    public String adminCreate(Authentication auth) {
        return "Об’єкт створено адміністратором: " + auth.getName();
    }

    // Тестові endpoints для заборон
    @GetMapping("/manage/forbidden")
    public String forbiddenForUser(Authentication auth) {
        return "Доступ заборонено користувачу " + auth.getName() + ": потрібно мати роль MANAGER або ADMIN";
    }

    @GetMapping("/admin/forbidden")
    public String forbiddenForManager(Authentication auth) {
        return "Доступ заборонено користувачу " + auth.getName() + ": потрібно мати роль ADMIN";
    }

    @GetMapping("/data/write/forbidden")
    public String forbiddenWrite(Authentication auth) {
        return "Доступ заборонено користувачу " + auth.getName() + ": тільки MANAGER або ADMIN можуть писати";
    }
}
