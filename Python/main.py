from controller import NoteController
from model import NoteModel
from view import NoteView

def main():
    model = NoteModel()
    view = NoteView()
    controller = NoteController(model, view)
    controller.run()

if __name__ == "__main__":
    main()