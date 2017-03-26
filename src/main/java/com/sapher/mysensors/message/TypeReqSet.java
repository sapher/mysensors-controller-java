package com.sapher.mysensors.message;

public enum TypeReqSet {

    /**
     * Temperature
     */
    V_TEMP(0),

    /**
     *  Humidity
     */
    V_HUM(1),

    /**
     * Binary status. 0=off 1=on
     */
    V_STATUS(2),

    /**
     * Percentage value. 0-100 (%)
     */
    V_PERCENTAGE(3),

    /**
     * Atmospheric Pressure
     */
    V_PRESSURE(4),

    /**
     * Whether forecast. One of "stable", "sunny", "cloudy", "unstable", "thunderstorm" or "unknown"
     */
    V_FORECAST(5),

    /**
     * Amount of rain
     */
    V_RAIN(6),

    /**
     * Rate of rain
     */
    V_RAINRATE(7),

    /**
     * Windspeed
     */
    V_WIND(8),

    /**
     * Gust
     */
    V_GUST(9),

    /**
     * Wind direction 0-360 (degrees)
     */
    V_DIRECTION(10),

    /**
     * UV light level
     */
    V_UV(11),

    /**
     * Weight (for scales etc)
     */
    V_WEIGHT(12),

    /**
     * Distance
     */
    V_DISTANCE(13),

    /**
     * Impedance value
     */
    V_IMPEDANCE(14),

    /**
     * Armed status of a security sensor. 1=Armed, 0=Bypassed
     */
    V_ARMED(15),

    /**
     * Tripped status of a security sensor. 1=Tripped, 0=Untripped
     */
    V_TRIPPED(16),

    /**
     * Watt value for power meters
     */
    V_WATT(17),

    /**
     * Accumulated number of KWH for a power meter
     */
    V_KWH(18),

    /**
     * Turn on a scene
     */
    V_SCENE_ON(19),

    /**
     *  Turn of a scene
     */
    V_SCENE_OFF(20),

    /**
     * Mode of header. One of "Off", "HeatOn", "CoolOn", or "AutoChangeOver"
     */
    V_HVAC_FLOW_STATE(21),

    /**
     * HVAC/Heater fan speed ("Min", "Normal", "Max", "Auto")
     */
    V_HVAC_SPEED(22),

    /**
     * Uncalibrated light level. 0-100%. Use V_LEVEL for light level in lux.
     */
    V_LIGHT_LEVEL(23),

    /**
     * Custom value	Any device
     */
    V_VAR1(24),

    /**
     * Custom value	Any device
     */
    V_VAR2(25),

    /**
     * Custom value	Any device
     */
    V_VAR3(26),

    /**
     * Custom value	Any device
     */
    V_VAR4(27),

    /**
     * Custom value	Any device
     */
    V_VAR5(28),

    /**
     * Window covering. Up
     */
    V_UP(29),

    /**
     * Window covering. Down
     */
    V_DOWN(30),

    /**
     * Window covering. Stop
     */
    V_STOP(31),

    /**
     * Send out an IR-command
     */
    V_IR_SEND(32),

    /**
     * This message contains a received IR-command
     */
    V_IR_RECEIVE(33),

    /**
     * Flow of water (in meter)
     */
    V_FLOW(34),

    /**
     * Water volume
     */
    V_VOLUME(35),

    /**
     * Set or get lock status. 1=Locked, 0=Unlocked
     */
    V_LOCK_STATUS(36),

    /**
     * Used for sending level-value	S_DUST, S_AIR_QUALITY, S_SOUND (dB), S_VIBRATION (hz), S_LIGHT_LEVEL (lux)
     */
    V_LEVEL(37),

    /**
     * Voltage level
     */
    V_VOLTAGE(38),

    /**
     * Current level
     */
    V_CURRENT(39),

    /**
     * RGB value transmitted as ASCII hex string (I.e "ff0000" for red)
     */
    V_RGB(40),

    /**
     * RGBW value transmitted as ASCII hex string (I.e "ff0000ff" for red + full white)
     */
    V_RGBW(41),

    /**
     * Optional unique sensor id (e.g. OneWire DS1820b ids)
     */
    V_ID(42),

    /**
     * Allows sensors to send in a string representing the unit prefix to be displayed in GUI. This is not parsed by controller! E.g. cm, m, km, inch
     */
    V_UNIT_PREFIX(43),

    /**
     * HVAC cold setpoint
     */
    V_HVAC_SETPOINT_COOL(44),

    /**
     * HVAC/Heater setpoint
     */
    V_HVAC_SETPOINT_HEAT(45),

    /**
     * Flow mode for HVAC ("Auto", "ContinuousOn", "PeriodicOn")
     */
    V_HVAC_FLOW_MODE(46),

    /**
     * Text message to display on LCD or controller device
     */
    V_TEXT(47),

    /**
     * Custom messages used for controller/inter node specific commands, preferably using S_CUSTOM device type.
     */
    V_CUSTOM(48),

    /**
     * GPS position and altitude. Payload: latitude;longitude;altitude(m). E.g. "55.722526;13.017972;18
     */
    V_POSITION(49),

    /**
     * Record IR codes S_IR for playback
     */
    V_IR_RECORD(50),

    /**
     * Water PH
     */
    V_PH(51),

    /**
     *  Water ORP : redox potential in mV
     */
    V_ORP(52),

    /**
     * Water electric conductivity μS/cm (microSiemens/cm)
     */
    V_EC(53),

    /**
     * Reactive power: volt-ampere reactive (var)
     */
    V_VAR(54),

    /**
     * Apparent power: volt-ampere (VA)
     */
    V_VA(55),

    /**
     * Ratio of real power to apparent power: floating point value in the range [-1,..,1]
     */
    V_POWER_FACTOR(56);

    private int value;

    TypeReqSet(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TypeReqSet fromValue(int value) {

        for (TypeReqSet typeReqSet : TypeReqSet.values()) {
            if(typeReqSet.getValue() == value) {
                return typeReqSet;
            }
        }

        return null;
    }
}
