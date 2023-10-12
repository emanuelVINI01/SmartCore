package com.emanuelvini.smart.shared.packet

import com.emanuelvini.smart.shared.action.ActionType

class ActionPacket (
    private val actionType : ActionType,
    private val target : String
) {


    fun toData(vararg arguments : String) : String {
        return "$actionType;$target;${arguments.joinToString (";")}}"
    }

}