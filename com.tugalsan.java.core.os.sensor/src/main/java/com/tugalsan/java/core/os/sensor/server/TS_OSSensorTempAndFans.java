package com.tugalsan.java.core.os.sensor.server;

import module jSensors;

public class TS_OSSensorTempAndFans {

    public static void main(String... a) {
        var components = JSensors.get.components();

        var disks = components.disks;
        if (disks != null) {
            disks.forEach(disk -> {
                System.out.println("Found Disk component: " + disk.name);
                if (disk.sensors != null) {
                    System.out.println("Sensors: ");
                    disk.sensors.loads.forEach(item -> {
                        System.out.println(item.name + ": " + item.value + " %");
                    });
                    disk.sensors.temperatures.forEach(item -> {
                        System.out.println(item.name + ": " + item.value + " C");
                    });
                    disk.sensors.fans.forEach(item -> {
                        System.out.println(item.name + ": " + item.value + " RPM");
                    });
                }
            });
        }

        var gpus = components.gpus;
        if (gpus != null) {
            gpus.forEach(gpu -> {
                System.out.println("Found Gpu component: " + gpu.name);
                if (gpu.sensors != null) {
                    System.out.println("Sensors: ");
                    gpu.sensors.loads.forEach(item -> {
                        System.out.println(item.name + ": " + item.value + " %");
                    });
                    gpu.sensors.temperatures.forEach(item -> {
                        System.out.println(item.name + ": " + item.value + " C");
                    });
                    gpu.sensors.fans.forEach(item -> {
                        System.out.println(item.name + ": " + item.value + " RPM");
                    });
                }
            });
        }

        var mobos = components.mobos;
        if (mobos != null) {
            mobos.forEach(mobo -> {
                System.out.println("Found Mobo component: " + mobo.name);
                if (mobo.sensors != null) {
                    System.out.println("Sensors: ");
                    mobo.sensors.loads.forEach(item -> {
                        System.out.println(item.name + ": " + item.value + " %");
                    });
                    mobo.sensors.temperatures.forEach(item -> {
                        System.out.println(item.name + ": " + item.value + " C");
                    });
                    mobo.sensors.fans.forEach(item -> {
                        System.out.println(item.name + ": " + item.value + " RPM");
                    });
                }
            });
        }

        var cpus = components.cpus;
        if (cpus != null) {
            cpus.forEach(cpu -> {
                System.out.println("Found CPU component: " + cpu.name);
                if (cpu.sensors != null) {
                    System.out.println("Sensors: ");
                    cpu.sensors.loads.forEach(item -> {
                        System.out.println(item.name + ": " + item.value + " %");
                    });
                    cpu.sensors.temperatures.forEach(item -> {
                        System.out.println(item.name + ": " + item.value + " C");
                    });
                    cpu.sensors.fans.forEach(item -> {
                        System.out.println(item.name + ": " + item.value + " RPM");
                    });
                }
            });
        }
    }
}
