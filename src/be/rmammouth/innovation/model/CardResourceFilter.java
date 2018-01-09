package be.rmammouth.innovation.model;

public class CardResourceFilter implements CardFilter
{
  private Resource resource;
  
  public CardResourceFilter(Resource resource) 
  {
    this.resource = resource;
  }

  @Override
  public boolean isFiltered(Card card) 
  {
    return !card.containsResource(resource);
  }

}
