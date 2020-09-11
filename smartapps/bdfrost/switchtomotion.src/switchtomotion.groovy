definition(
    name: "Switch to Motion",
    namespace: "bdfrost",
    author: "Brian D. Frost",
    description: "Ties motion detection to switch state",
    category: "Safety & Security",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/intruder_motion-presence.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/intruder_motion-presence@2x.png"
)

preferences {
    section("When this switch is turned on or off") {
        input "master", "capability.switch", title: "Which switch?", multiple: false, required: true
    }
    section("Turn on or off all of these motion detectors") {
        input "motiondetectors", "capability.motionSensor", title: "Which motion detector?", multiple: true, required: true
    }
}

def installed()
{   
    subscribe(master, "switch.on", onHandler)
    subscribe(master, "switch.off", offHandler)
}

def updated()
{
    unsubscribe()
    subscribe(master, "switch.on", onHandler)
    subscribe(master, "switch.off", offHandler)
}

def onHandler(evt) {
    log.debug evt.value
    motiondetectors?.active()
}

def offHandler(evt) {
    log.debug evt.value
    motiondetectors?.inactive()
}
