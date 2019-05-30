public interface Listable
{
  void addItem(String newItem);
  boolean removeItem(String item);
  boolean containsItem(String item);
  String selectItem(int pos);
}
