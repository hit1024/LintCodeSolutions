
import heapq


class Node:
    def __init__(self, value, idx_arrs, idx_arr):
        self.value = value
        self.idx_arrs = idx_arrs
        self.idx_arr = idx_arr

    def __cmp__(self, other):
        return cmp(other.value, self.value)


class Solution:
    # @param {int[][]} arrays a list of array
    # @param {int} k an integer
    # @return {int} an integer, K-th largest element in N arrays
    def KthInArrays(self, arrays, k):
        '''use max heap'''
        queue = []
        heapq.heapify(queue)

        n = len(arrays)

        for idx_arrs, array in enumerate(arrays):
            if not array:
                continue

            ln_arr = len(array)
            array.sort()

            idx_arr = ln_arr - 1
            heapq.heappush(queue, Node(array[idx_arr], idx_arrs, idx_arr))
        
        for i in xrange(k):
            node = heapq.heappop(queue)
            value = node.value
            idx_arrs = node.idx_arrs
            idx_arr = node.idx_arr

            if i == k - 1:
                return value

            if idx_arr:
                heapq.heappush(queue, Node(arrays[idx_arrs][idx_arr-1], idx_arrs, idx_arr-1))

