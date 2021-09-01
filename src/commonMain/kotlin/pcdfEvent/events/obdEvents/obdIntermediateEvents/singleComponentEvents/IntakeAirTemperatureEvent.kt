package pcdfEvent.events.obdEvents.obdIntermediateEvents.singleComponentEvents

import pcdfEvent.events.obdEvents.OBDCommand
import pcdfEvent.events.obdEvents.obdIntermediateEvents.OBDIntermediateEvent
import pcdfPattern.PCDFDataPattern
import pcdfPattern.PCDFPattern

class IntakeAirTemperatureEvent(
    source: String,
    timestamp: Long,
    bytes: String,
    pid: Int,
    mode: Int,
    var temperature: Int
) : OBDIntermediateEvent(source, timestamp, bytes, pid, mode) {

    // Secondary constructor only used by convert function.
    constructor(
        data: String,
        source: String,
        timestamp: Long,
        bytes: String,
        pid: Int,
        mode: Int
    ) : this(source, timestamp, bytes, pid, mode, -1) {
        temperature = (data.substring(0, 2).toInt(16) - 40)
    }

    override fun getPattern(): PCDFPattern {
        val pattern = super.getPattern()
        pattern.data = PCDFDataPattern(
            bytes = bytes,
            mode = mode,
            pid = pid,
            intake_air_temperature = temperature
        )
        return pattern
    }

    override fun toString(): String {
        return super.toString() + "Intake Air Temperature: $temperature " +
                OBDCommand.INTAKE_AIR_TEMPERATURE.unit
    }
}