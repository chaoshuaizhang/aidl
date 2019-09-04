// IBookManager.aidl
package net.shopin.myaidlserver;

// Declare any non-default types here with import statements
import net.shopin.myaidlserver.Book;
interface IBookManager {
    List<Book> getBookList();

    void addBook(in Book book);
}
