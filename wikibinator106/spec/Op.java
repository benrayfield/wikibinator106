package wikibinator106.spec;

/** max 32 ops, or 64 if you count clean vs dirty forms of them */
public enum Op{
	
	isLeaf,
	
	isClean,
	
	isCleanLeaf,
	
	isDirtyLeaf,
	
	asClean,
	
	asDirty,
	
	cleanCall,
	
	trecurse,
	
	getComment,
	
	setComment,
	
	/** returns cbt8 */
	curriesAll,
	
	axA,
	
	axB,
	
	fpr;
	
	

}
