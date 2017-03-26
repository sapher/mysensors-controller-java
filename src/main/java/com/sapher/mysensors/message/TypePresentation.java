package com.sapher.mysensors.message;

public enum TypePresentation {

    /**
     * Door and window sensors
     */
    S_DOOR(0),

    /**
     * Motion sensors
     */
    S_MOTION(1),

    /**
     * Smoke sensor
     */
    S_SMOKE(2),

    /**
     * Binary device (on/off)
     */
    S_BINARY(3),

    /**
     * Dimmable device of some kind
     */
    S_DIMMER(4),

    /**
     * Window covers or shades
     */
    S_COVER(5),

    /**
     * Temperature sensor
     */
    S_TEMP(6),

    /**
     * Humidity sensor
     */
    S_HUM(7),

    /**
     * Barometer sensor (Pressure)
     */
    S_BARO(8),

    /**
     * Wind sensor
     */
    S_WIND(9),

    /**
     * Rain sensor
     */
    S_RAIN(10),

    /**
     * UV sensor
     */
    S_UV(11),

    /**
     *  Weight sensor for scales etc
     */
    S_WEIGHT(12),

    /**
     * Power measuring device, like power meters
     */
    S_POWER(13),

    /**
     *  Heater device
     */
    S_HEATER(14),

    /**
     *  Distance sensor
     */
    S_DISTANCE(15),

    /**
     *  Light sensor
     */
    S_LIGHT_LEVEL(16),

    /**
     *  Arduino node device
     */
    S_ARDUINO_NODE(17),

    /**
     *  Arduino repeating node device
     */
    S_ARDUINO_REPEATER_NODE(18),

    /**
     *  Lock device
     */
    S_LOCK(19),

    /**
     *  Ir sender/receiver device
     */
    S_IR(20),

    /**
     *  Water meter
     */
    S_WATER(21),

    /**
     *  Air quality sensor e.g. MQ-2
     */
    S_AIR_QUALITY(22),

    /**
     * Use this for custom sensors where no other fits.
     */
    S_CUSTOM(23),

    /**
     *  Dust level sensor
     */
    S_DUST(24),

    /**
     * Scene controller device
     */
    S_SCENE_CONTROLLER(25),

    /**
     *  RGB light
     */
    S_RGB_LIGHT(26),

    /**
     *  RGBW light (with separate white component)
     */
    S_RGBW_LIGHT(27),

    /**
     *  Color sensor	V_RGB
     */
    S_COLOR_SENSOR(28),

    /**
     *  Thermostat/HVAC device
     */
    S_HVAC(29),

    /**
     *  Multimeter device
     */
    S_MULTIMETER(30),

    /**
     *  Sprinkler device
     */
    S_SPRINKLER(31),

    /**
     *  Water leak sensor
     */
    S_WATER_LEAK(32),

    /**
     * Sound sensor
     */
    S_SOUND(33),

    /**
     *  Vibration sensor
     */
    S_VIBRATION(34),

    /**
     *  Moisture sensor
     */
    S_MOISTURE(35),

    /**
     *  LCD text device
     */
    S_INFO(36),

    /**
     * Gas meter
     */
    S_GAS(37),

    /**
     *  GPS Sensor
     */
    S_GPS(38),

    /**
     * Water quality sensor
     */
    S_WATER_QUALITY(39);

    private int value;

    TypePresentation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TypePresentation fromValue(int value) {

        for (TypePresentation typePresentation : TypePresentation.values()) {
            if(typePresentation.getValue() == value) {
                return typePresentation;
            }
        }

        return null;
    }
}
