package business

import entity.contactEntity
import repository.contactRepository

class contactBusiness{



    private fun validateSave(Nome: String, Telefone: String) {
        if (Nome == "") {
            throw Exception("Nome é obrigatorio")
        }
        if (Telefone == "") {
            throw Exception("Telefone é obrigatorio")
        }
    }

    private fun validateRemove(Nome: String, Telefone: String) {
        if (Nome == "" || Telefone == "" ) {
            throw Exception("é necessario selecionar um contato antes de remover")
        }
        if (Telefone == "") {
            throw Exception("Telefone é obrigatorio")
        }
    }

    fun save(Nome: String, Telefone: String){
        validateSave(Nome,Telefone)
        val contact = contactEntity(Nome,Telefone)
        contactRepository.contactSave(contact)
    }

    fun delete(Nome: String, Telefone: String){
        validateRemove(Nome, Telefone)
        val contact = contactEntity(Nome , Telefone)
        contactRepository.contactRemove(contact)
    }

    fun getList(): List<contactEntity>{
        return contactRepository.getList()
    }
}
