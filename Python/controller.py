class NoteController:
    def __init__(self, model, view):
        self.model = model
        self.view = view

    def add_note(self):
        title = self.view.get_input("Введите заголовок заметки: ")
        body = self.view.get_input("Введите текст заметки: ")
        self.model.add_note(title, body)
        self.model.save_notes()
        self.view.show_message("Заметка добавлена.")

    def list_notes(self):
        notes = self.model.get_notes()
        self.view.show_notes(notes)

    def view_note(self):
        note_id = int(self.view.get_input("Введите ID заметки для просмотра: "))
        note = self.model.get_notes()[note_id - 1]
        self.view.show_note_details(note)

    def edit_note(self):
        note_id = int(self.view.get_input("Введите ID заметки для редактирования: "))
        note = self.model.get_notes()[note_id - 1]
        title = self.view.get_input("Введите новый заголовок заметки: ")
        body = self.view.get_input("Введите новый текст заметки: ")
        self.model.edit_note(note_id, title, body)
        self.model.save_notes()
        self.view.show_message("Заметка отредактирована.")

    def delete_note(self):
        note_id = int(self.view.get_input("Введите ID заметки для удаления: "))
        self.model.delete_note(note_id)
        self.model.save_notes()
        self.view.show_message("Заметка удалена.")

    def list_notes_by_date(self):
        date = self.view.get_input("Введите дату в формате YYYY-MM-DD: ")
        notes = self.model.get_notes_by_date(date)
        if notes:
            self.view.show_notes_by_date(notes)
        else:
            self.view.show_message("Заметок на указанную дату не найдено.")

    def list_notes_option(self):
        option = self.view.get_input("Выберите опцию:\n1. Показать все заметки\n2. Показать заметки по дате\n3. Показать заметку по ID\n Выберите действие: ")
        if option == "1":
            self.list_notes()
        elif option == "2":
            self.list_notes_by_date()
        elif option == "3":
                self.view_note()
        else:
            self.view.show_message("Неверный выбор. Пожалуйста, выберите снова.")

    
    def run(self):
        self.model.load_notes()
        while True:
            print("\n1. Добавить заметку")
            print("2. Показать заметки ")
            print("3. Редактировать заметку")
            print("4. Удалить заметку")
            print("5. Выйти")
            

            choice = self.view.get_input("Выберите действие: ")

            if choice == "1":
                self.add_note()
            elif choice == "2":
                self.list_notes_option()
            elif choice == "3":
                self.edit_note()
            elif choice == "4":
                self.delete_note()
            elif choice == "5":
                break
            else:
                self.view.show_message("Неверный выбор. Пожалуйста, выберите снова.")
