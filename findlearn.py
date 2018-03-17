'''
The tree struct
                   A
            B           C
        D           E       F
'''
class Node(object):
	"""docstring for Node"""
	def __init__(self, value):
		super(Node, self).__init__()
		self.value = value
		self.children = []
		pass

	def show(self):
		print (self.value)
		pass


class Tree(object):
	"""docstring for Tree"""
	def __init__(self, nodes):
		super(Tree, self).__init__()
		self.nodes = []
		self.nodes = nodes

	def depth_first(self, node):
		node.show()
		if not node.children:
			return

		for child in node.children:
		 	self.depth_first(child)
		pass

	def breadth_first(self, node):
		node.show()
		worklist = node.children
		templist = []

		while worklist:
			for child_node in worklist:
				child_node.show()
				print(child_node.children)
				templist.extend(child_node.children)
				print(templist)
			worklist = templist.copy()
			templist.clear()
			pass

		pass


def init_relation(nodes_list):
	# A's children is B,C
	nodes_list[0].children.append(nodes_list[1])
	nodes_list[0].children.append(nodes_list[2])
	# B's children is D
	nodes_list[1].children.append(nodes_list[3])
	# C's children is E,F
	nodes_list[2].children.append(nodes_list[4])
	nodes_list[2].children.append(nodes_list[5])
	pass


def main():
	nodes_list = []
	nodes_list.append(Node("A"))
	nodes_list.append(Node("B"))
	nodes_list.append(Node("C"))
	nodes_list.append(Node("D"))
	nodes_list.append(Node("E"))
	nodes_list.append(Node("F"))
	init_relation(nodes_list)
	
	tree = Tree(nodes_list)
	# tree.depth_first(tree.nodes[0])
	tree.breadth_first(tree.nodes[0])
	pass


if __name__ == '__main__':
	main()

