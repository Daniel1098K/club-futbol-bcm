package com.AtleticoBucaramanga.byDanielPerez.club_futbol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("=".repeat(60));
        System.out.println("⚽ ATLÉTICO BUCARAMANGA - SISTEMA DE GESTIÓN");
        System.out.println("=".repeat(60));
        System.out.println("✅ Aplicación iniciada correctamente");
        System.out.println("🌐 URL: http://localhost:8100");
        System.out.println("📊 Base de Datos: MySQL Online");
        System.out.println("👤 Desarrollado por: Daniel Pérez");
        System.out.println("=".repeat(60));
        System.out.println("\n🔗 RELACIONES JPA IMPLEMENTADAS:");
        System.out.println("   • @OneToOne  : Club ↔️ Entrenador");
        System.out.println("   • @OneToMany : Club → Jugadores");
        System.out.println("   • @ManyToOne : Club → Asociación");
        System.out.println("   • @ManyToMany: Club ↔️ Competiciones");
        System.out.println("=".repeat(60));
    }
}