// IBookManager.aidl
package net.shopin.myaidlserver;

// Declare any non-default types here with import statements
import net.shopin.myaidlserver.Book;
import net.shopin.myaidlserver.BookListener;
interface IBookManager {
    List<Book> getBookList();

    void addBook(in Book book);

    void addListener(BookListener listener);

    void removeListener(BookListener listener);
}
