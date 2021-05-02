package repository

import entity.contactEntity

class contactRepository {
    //como manter sempre os dados salvos
    companion object{
        private val contactList = mutableListOf<contactEntity>()

        fun contactSave(contact: contactEntity){
            contactList.add(contact)
        }
        fun contactRemove(contact: contactEntity){
            contactList.filter { contact.nome == it.nome }
        }
        //especificando q vai retornar uma lista de contatos
        fun getList (): List<contactEntity>{
            return contactList
        }
    }


}