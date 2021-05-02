package repository

import entity.contactEntity

class contactRepository {
    //como manter sempre os dados salvos
    companion object {
        private val contactList = mutableListOf<contactEntity>()

        fun contactSave(contact: contactEntity) {
            contactList.add(contact)
        }

        fun contactRemove(contact: contactEntity) {
            //withIndex me da opções magicas q sao values e index
            // value para acessar os itens dentro do item
            for (item in contactList.withIndex()){
                if(item.value.nome == contact.nome && item.value.telefone == contact.telefone){
                    contactList.removeAt(item.index)
                    break
                }

            }



            //funciona mas nao é recomendado
            //contactList.remove(contact)

            /* essa é uma das formas, mas a foma kotlin bonita
            // para achar o index vai aumentando ele ate achar ele na lista, qnd achar quebra a listagem
            var index = 0

            for (item in contactList){
                if(item.nome == contact.nome && item.telefone == contact.telefone){
                    break
                }
                index ++
            }
            contactList.removeAt(index)
            */

        }

        //especificando q vai retornar uma lista de contatos
        fun getList(): List<contactEntity> {
            return contactList
        }
    }


}