package asrsSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
public class RecentItems {
	
    public interface RecentItemsObserver
    {
        void onRecentItemChange(RecentItems src);
    }
    
    public final static String RECENT_ITEM_STRING = "recent.item."; //$NON-NLS-1$
    
    private int                       m_maxItems;
    private Preferences               m_prefNode;

    private List<String>              m_items            = new ArrayList<String>();
    private List<RecentItemsObserver> m_observers        = new ArrayList<RecentItemsObserver>();
    
    public RecentItems(int maxItems, Preferences prefNode)
    {
        m_maxItems = maxItems;
        m_prefNode = prefNode;
        
        loadFromPreferences();
    }
    
    public void push(String item)
    {
        m_items.remove(item);
        m_items.add(0, item);
        
        if (m_items.size() > m_maxItems)
        {
            m_items.remove(m_items.size() - 1);
        }
        
        update();
    }
    
    public void remove(Object item)
    {
        m_items.remove(item);
        update();
    }
    
    public String get(int index)
    {
        return (String)m_items.get(index);
    }
    
    public List<String> getItems()
    {
        return m_items;
    }
    
    public int size()
    {
        return m_items.size();
    }
    
    public void addObserver(RecentItemsObserver observer)
    {
        m_observers.add(observer);
    }
    
    public void removeObserver(RecentItemsObserver observer)
    {
        m_observers.remove(observer);
    }
    
    private void update()
    {
        for (RecentItemsObserver observer : m_observers)
        {
            observer.onRecentItemChange(this);
        }
        
        storeToPreferences();
    }
    
    private void loadFromPreferences()
    {
        // load recent files from properties
        for (int i = 0; i < m_maxItems; i++)
        {
            String val = m_prefNode.get(RECENT_ITEM_STRING+i, ""); //$NON-NLS-1$

            if (!val.equals("")) //$NON-NLS-1$
            {
                m_items.add(val);
            }
            else
            {
                break;
            }
        }
    }
    
    private void storeToPreferences()
    {
        for (int i = 0; i < m_maxItems; i++)
        {
            if (i < m_items.size())
            {
                m_prefNode.put(RECENT_ITEM_STRING+i, (String)m_items.get(i));
            }
            else
            {
                m_prefNode.remove(RECENT_ITEM_STRING+i);
            }
        }
    }
}
}
