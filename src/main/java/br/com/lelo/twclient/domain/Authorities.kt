package br.com.lelo.twclient.domain

enum class Authorities {

    ROLE_USER, ROLE_ADMIN;

    companion object {

        fun names(): Array<String?> {
            val names = arrayOfNulls<String>(values().size)
            for (index in 0 until values().size) {
                names[index] = values()[index].name
            }

            return names
        }
    }
}