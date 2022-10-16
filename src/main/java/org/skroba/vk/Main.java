package org.skroba.vk;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.getenv().getOrDefault("VK_ACCESS_TOKEN", "-"));
    }
}
