class Solution:
    def minimumEffort(self, tasks: List[List[int]]) -> int:
        # Prioritize tasks that require a larger energy buffer first
        tasks.sort(
            key=lambda task: task[1] - task[0],
            reverse=True
        )

        minimum_initial_energy = 0
        available_energy = 0

        for energy_cost, required_energy in tasks:

            # Ensure enough energy exists before starting the task
            if available_energy < required_energy:
                additional_energy_needed = (
                    required_energy - available_energy
                )

                minimum_initial_energy += additional_energy_needed
                available_energy = required_energy

            # Spend energy to complete the task
            available_energy -= energy_cost

        return minimum_initial_energy