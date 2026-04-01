class Solution:
    def survivedRobotsHealths(self, positions: list[int], healths: list[int], directions: str) -> list[int]:
        n = len(positions)
        
        # Zip all properties together with the original index, then sort by position
        robots = []
        for i in range(n):
            robots.append([positions[i], healths[i], directions[i], i])
            
        robots.sort() # Sorts primarily by position (the first element in the list)
        
        stack = []
        
        for robot in robots:
            # robot = [position, health, direction, original_index]
            if robot[2] == 'R':
                stack.append(robot)
            else:
                # The current robot is moving 'L'. It will crush into any 'R' robots in the stack.
                survived = True
                while stack and stack[-1][2] == 'R':
                    # Compare healths
                    if stack[-1][1] > robot[1]:
                        stack[-1][1] -= 1  # Right robot wins, loses 1 health
                        survived = False
                        break
                    elif stack[-1][1] < robot[1]:
                        stack.pop()        # Left robot wins, destroys right robot
                        robot[1] -= 1      # Left robot loses 1 health, keeps checking stack
                    else:
                        stack.pop()        # Tie: Both are destroyed
                        survived = False
                        break
                
                # If the 'L' robot survived all collisions, it gets added to the stack
                if survived:
                    stack.append(robot)
                    
        # Sort the surviving robots by their original_index
        stack.sort(key=lambda x: x[3])
        
        # Extract and return the final healths
        return [robot[1] for robot in stack]