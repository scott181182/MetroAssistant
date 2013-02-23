
public enum ClassType 
{
	MATH(Resource.FORMULAS),
	SCIENCE(Resource.FORMULAS, Resource.PERTABLE),
	ENGLISH(Resource.FIGLANG),
	FORIEGN_LANGUAGE(Resource.NULL),
	SOCIAL_SCIENCE(Resource.GOVERNMENT),
	ELECTIVE(Resource.NULL);
	
	private int resource;
	private int[] resources;
	private ClassType(int... rsrc)
	{
		if(rsrc.length == 1) { resource = rsrc[0]; }
		else { resources = rsrc; }
	}
	public int getResource() { return this.resource; }
	public int[] getResources() { return this.resources; }
	public void setResource(int par1) { this.resource = par1; }
	public void setResources(int[] par1) { this.resources = par1; }
}
