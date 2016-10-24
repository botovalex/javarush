package com.javarush.test.level32.lesson15.big01;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void init() {
        createNewDocument();
    }

    public void exit() {
        System.exit(0);
    }

    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        HTMLEditorKit editor = new HTMLEditorKit();
        document = (HTMLDocument) editor.createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text) {
        resetDocument();
        StringReader reader = new StringReader(text);
        HTMLEditorKit editorKit = new HTMLEditorKit();
        try {
            editorKit.read(reader, document, 0);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        } catch (BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText() {
        StringWriter writer = new StringWriter();
        HTMLEditorKit editorKit = new HTMLEditorKit();
        try {
            editorKit.write(writer, document, 0, document.getLength());
        } catch (IOException e) {
            ExceptionHandler.log(e);
        } catch (BadLocationException e) {
            ExceptionHandler.log(e);
        }
        return writer.toString();
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            HTMLEditorKit editorKit = new HTMLEditorKit();
            try {
                FileReader reader = new FileReader(currentFile);
                editorKit.read(reader, document, 0);
            } catch (FileNotFoundException e) {
                ExceptionHandler.log(e);
            } catch (BadLocationException e) {
                ExceptionHandler.log(e);
            } catch (IOException e) {
                ExceptionHandler.log(e);
            }
            view.resetUndo();
        }
    }

    public void saveDocument() {
        view.selectHtmlTab();
        if (currentFile == null) {
            saveDocumentAs();
        } else {
            view.setTitle(currentFile.getName());
            HTMLEditorKit editorKit = new HTMLEditorKit();
            try {
                FileWriter writer = new FileWriter(currentFile);
                editorKit.write(writer, document, 0, document.getLength());
                writer.close();
            } catch (IOException e) {
                ExceptionHandler.log(e);
            } catch (BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        if (fileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            HTMLEditorKit editorKit = new HTMLEditorKit();
            try {
                FileWriter writer = new FileWriter(currentFile);
                editorKit.write(writer, document, 0, document.getLength());
                writer.close();
            } catch (IOException e) {
                ExceptionHandler.log(e);
            } catch (BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }
}

/*
23.2.	Пришло время реализовать метод openDocument(). Метод должен работать
аналогично методу saveDocumentAs(), в той части, которая отвечает за выбор файла.
Подсказка: Обрати внимание на имя метода для показа диалогового окна.
Когда файл выбран, необходимо:
23.2.1.	Установить новое значение currentFile.
23.2.2.	Сбросить документ.
23.2.3.	Установить имя файла в заголовок у представления.
23.2.4.	Создать FileReader, используя currentFile.
23.2.5.	Вычитать данные из FileReader-а в документ document с помощью объекта класса
HTMLEditorKit.
23.2.6.	Сбросить правки (вызвать метод resetUndo представления).
23.2.7.	Если возникнут исключения - залогируй их и не пробрасывай наружу.
Проверь работу пунктов меню Сохранить и Открыть.
 */