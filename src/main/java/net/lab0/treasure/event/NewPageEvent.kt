package net.lab0.treasure.event

import org.springframework.context.ApplicationEvent

class NewPageEvent(source: Any, val location: String) : ApplicationEvent(source)